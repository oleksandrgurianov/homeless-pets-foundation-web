import React, {useEffect, useState} from 'react'
import useAuth from '../hooks/useAuth'
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

let stompClient;

const Notification = () => {
    const {auth} = useAuth();

    const email = auth?.email;

    const role = auth?.role;

    const [userData, setUserData] = useState({
        email: email,
        role: role,
        connected: false
    });

    useEffect(() => {
        console.log(userData);
    }, [userData]);

    const connect = () => {
        const socket = new SockJS('http://localhost:8080/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }

    useEffect(() => {
        connect();
    }, []);

    const onConnected = () => {
        setUserData({...userData, 'connected': true});
        stompClient.subscribe('/notifications/messages', onPrivateNotification);
    }

    const onPrivateNotification = (payload) => {
        const payloadData = JSON.parse(payload.body);
        setUserData({...userData, 'connected': true});

        if (role === payloadData.receiverRole) {
            const message = payloadData.message.toLowerCase().slice(0, -1);
            alert('A new ' + message + ' has been added.\nCheck it out!');
            console.log(payload);
        }
    }

    const onError = (err) => {
        console.log(err);
    }

    return null;
}

export default Notification
