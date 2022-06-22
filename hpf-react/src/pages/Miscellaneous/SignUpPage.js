// import React, {useState} from 'react'
// import axios from 'axios'
// import {Link} from 'react-router-dom'
// import '../styles/SignUpPage.css'
// import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
// import {faCirclePlus} from '@fortawesome/free-solid-svg-icons'
// import loading from '../images/loading.gif'
//
// const SignUpPage = () => {
//     const [avatar, setAvatar] = useState('');
//
//     const [fullName, setFullName] = useState('');
//
//     const [email, setEmail] = useState('');
//
//     const [phoneNumber, setPhoneNumber] = useState('');
//
//     const [password, setPassword] = useState('');
//
//     const [confirmPassword, setConfirmPassword] = useState('');
//
//     return (
//         <>
//             <div className={'SignUp'}>
//                 <h1>Sign Up</h1>
//                 <form className={'SignUpForm'}>
//                     <div className={'form-avatar'}>
//                         <p>Avatar</p>
//                         <img src={avatar} alt={''}/>
//                         <label htmlFor={'addAvatarButton'}><FontAwesomeIcon className={'avatar-add-icon'} icon={faCirclePlus}/></label>
//                         <input type={'file'} id={'addAvatarButton'} accept={'image/jpeg, image/png'}/>
//                     </div>
//                     <input
//                         type={'text'}
//                         name={'fullName'}
//                         placeholder={'Full Name *'}
//                         value={fullName}
//                         onChange={(e) => setFullName(e.target.value)}
//                         required
//                     />
//                     <input
//                         type={'email'}
//                         name={'email'}
//                         placeholder={'Email *'}
//                         value={email}
//                         onChange={(e) => setEmail(e.target.value)}
//                         required
//                     />
//                     <input
//                         type={'tel'}
//                         name={'phoneNumber'}
//                         placeholder={'Phone Number *'}
//                         value={phoneNumber}
//                         onChange={(e) => setPhoneNumber(e.target.value)}
//                         required
//                     />
//                     <input
//                         type={'password'}
//                         name={'password'}
//                         placeholder={'Password *'}
//                         value={password}
//                         onChange={(e) => setPassword(e.target.value)}
//                         required
//                     />
//                     <input
//                         type={'password'}
//                         name={'confirmPassword'}
//                         placeholder={'Confirm Password *'}
//                         value={confirmPassword}
//                         onChange={(e) => setConfirmPassword(e.target.value)}
//                         required
//                     />
//                     <button>Sign Up</button>
//                 </form>
//                 <hr className={'SignUpLine'}/>
//                 <p>Already have an account? <Link className={'SignUpLink'} to={'/logIn'}>Log In.</Link></p>
//             </div>
//         </>
//     )
// }
//
// export default SignUpPage