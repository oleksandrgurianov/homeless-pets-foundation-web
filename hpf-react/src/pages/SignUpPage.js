import React, {useState} from 'react'
import {Link} from 'react-router-dom'
import '../styles/SignUpPage.css'

const SignUpPage = () => {
    const [email, setEmail] = useState('')

    const [password, setPassword] = useState('');

    return (
        <>
            <div className={'LogIn'}>
                <h1>Log In</h1>
                <form className={'LogInForm'}>
                    <input
                        type={'email'}
                        name={'email'}
                        placeholder={'Email *'}
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        type={'password'}
                        name={'password'}
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
    )
}

export default SignUpPage