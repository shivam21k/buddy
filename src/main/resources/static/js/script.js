'use strict';

var loginPage = document.querySelector('#login-page');
var chatPage = document.querySelector('#chat-page');
var loginForm = document.querySelector('#loginForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

function connect(event) {
try{
    username = document.querySelector('#name').value.trim();
    if (username) {
        loginPage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('http://localhost:8080/ws-chatapp');//was failing here - 'added http://localhost:8080'
        //err = SyntaxError: The URL '/ws-chatapp' is invalid at new r (https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js:2:9312) at HTMLFormElement.connect (file:///C:/Users/Acer/Desktop/buddy/src/main/resources/static/js/script.js:22:22)
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
    }
catch(err){
    console.log(err);
}
}

function onConnected() {
    stompClient.subscribe('/topic/chat', onMessageReceived);
    stompClient.send("/app/addUser",
        {},
        JSON.stringify({chatUser: username, messageAction: 'JOIN'})
    );

    connectingElement.classList.add('hidden');
}

function onError() {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            chatUser: username,
            message: messageInput.value,
            messageAction: 'MESSAGE',
            messageTime: null
        };
        stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.messageAction === 'JOIN') {
        messageElement.classList.add('event-message');
        message.message = message.chatUser + ' joined!';
    } else if (message.messageAction === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.message = message.chatUser + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.chatUser);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var iTag = document.createElement("i");
        var messageTime = document.createTextNode(' ' + message.messageTime);
        iTag.append(messageTime);
        messageElement.appendChild(iTag);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.message);

    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

loginForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);