import React, {useEffect, useState} from 'react'
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

// Set the backend location
const ENDPOINT = 'http://localhost:8080/ws'

const Notification = () => {
    const [stompClient, setStompClient] = useState(null);

    const [msgToSend, setSendMessage] = useState("Enter your message here!");


    useEffect(() => {
        // use SockJS as the websocket client
        const socket = SockJS(ENDPOINT);

        // Set stomp to use websockets
        const stompClient = Stomp.over(socket);

        // connect to the backend
        stompClient.connect({}, () => {
            // subscribe to the backend
            stompClient.subscribe('/topic/greetings', (data) => {
                console.log(data);
                onMessageReceived(data);
            });
        });

        // maintain the client for sending and receiving
        setStompClient(stompClient);
    }, []);

    // send the data using Stomp
    function sendMessage() {
        stompClient.send("/app/hello", {}, JSON.stringify({'name': msgToSend}));
    }

    // display the received data
    function onMessageReceived(data) {
        const result = JSON.parse(data.body);
        alert(result.content)
    }

    return (
        <>
            <input onChange={(event) => setSendMessage(event.target.value)}/>
            <button onClick={sendMessage}>Send</button>
        </>
    );
}

export default Notification
