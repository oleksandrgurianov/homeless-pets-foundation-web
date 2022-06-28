import {initializeApp} from 'firebase/app'
import {getStorage} from 'firebase/storage'

const firebaseConfig = {
    apiKey: 'AIzaSyByJBuZ4LCZ8S42cQPzxc_fdftleGXfznY',
    authDomain: 'individual-track-assignment.firebaseapp.com',
    projectId: 'individual-track-assignment',
    storageBucket: 'individual-track-assignment.appspot.com',
    messagingSenderId: '1004201470406',
    appId: '1:1004201470406:web:7432673a491f28e9bcaac7'
};

const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);