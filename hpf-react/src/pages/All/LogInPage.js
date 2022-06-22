import React, {useRef, useState, useEffect} from 'react'
import {Link, useNavigate} from 'react-router-dom'
import '../../styles/All/LogInPage.css'
import axios from 'axios'

const LogInPage = () => {
    const navigate = useNavigate();

    const emailRef = useRef();

    const [email, setEmail] = useState('')

    const [password, setPassword] = useState('');

    useEffect(() => {
        emailRef.current.focus();
    }, [])

    const handleSubmit = async (e) => {
        e.preventDefault();

        axios.post(`http://localhost:8080/login`, {
            "email": email,
            "password": password
        })
            .then(res => {
                const token = res.data.accessToken;
                const role = res.data.role;
                const userId = res.data.userId;
                localStorage.setItem('email', email);
                localStorage.setItem('token', token);
                localStorage.setItem('role', role);
                localStorage.setItem('userId', userId);
                navigate('/');
            })
            .catch(err => {
                if (!err?.response) {
                    alert('There was an error connecting to the HPF server.');
                } else if (err.response?.status === 400) {
                    alert('Your email or password is incorrect.');
                } else if (err.response?.status === 401) {
                    alert('You are not authorized');
                } else {
                    alert('An unknown error occurred.')
                }

                console.log(err);
            });
    }

    return (
        <>
            <div className={'LogIn'}>
                <h1>Log In</h1>
                <form className={'LogInForm'} onSubmit={handleSubmit}>
                    <input
                        type={'email'}
                        id={'email'}
                        ref={emailRef}
                        placeholder={'Email *'}
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        type={'password'}
                        id={'password'}
                        placeholder={'Password *'}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <button>Log In</button>
                </form>
                <hr className={'LogInLine'}/>
                <p>Don't have an account? <Link className={'LogInLink'} to={'/signUp'}>Sign Up.</Link></p>
            </div>
        </>
    );
}

export default LogInPage