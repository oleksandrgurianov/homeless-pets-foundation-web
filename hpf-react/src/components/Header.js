import React from 'react'
import {Link, useNavigate} from 'react-router-dom'
import logo from '../images/logo.png'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCaretDown} from '@fortawesome/free-solid-svg-icons'
import useAuth from '../hooks/useAuth'
import Notification from './Notification'

function Header() {
    const {auth} = useAuth();

    const {setAuth} = useAuth();

    const navigate = useNavigate();

    const logOut = () => {
        setAuth({});
        alert('You\'ve been logged out');
        navigate('/');
    }

    function showPetsDropdown() {
        document.getElementById('petsDropdown').classList.toggle('show');
    }

    function showAccountDropdown() {
        document.getElementById('accountDropdown').classList.toggle('show');
    }

    window.onclick = function (e) {
        if (!e.target.matches('.pets-dropdown-button')) {
            let petsDropdown = document.getElementById('petsDropdown');

            if (petsDropdown != null) {
                if (petsDropdown.classList.contains('show')) {
                    petsDropdown.classList.remove('show');
                }
            }
        }

        if (!e.target.matches('.account-dropdown-button')) {
            let accountDropdown = document.getElementById('accountDropdown');

            if (accountDropdown != null) {
                if (accountDropdown.classList.contains('show')) {
                    accountDropdown.classList.remove('show');
                }
            }
        }
    }

    return (
        <>
            <div className={'NavBar'}>
                <Link className={'NavLogo'} to={'/'}>
                    <img src={logo} alt={'homeless pets foundation logo'}/>
                </Link>
                <Link className={'NavLink'} to={'/'}>Home</Link>
                <div className={'NavPetsDropdown'}>
                    <button className={'pets-dropdown-button'} onClick={showPetsDropdown}>Pets<FontAwesomeIcon
                        className={'dropdown-button-icon'} icon={faCaretDown}/></button>
                    <div className={'pets-dropdown-content'} id={'petsDropdown'}>
                        <Link to={'/pets/categories/dogs'}>Dogs</Link>
                        <hr/>
                        <Link to={'/pets/categories/cats'}>Cats</Link>
                        <hr/>
                        <Link to={'/pets/categories/rabbits'}>Rabbits</Link>
                        <hr/>
                        <Link to={'/pets/categories/rats'}>Rats</Link>
                        <hr/>
                        <Link to={'/pets/categories/parrots'}>Parrots</Link>
                    </div>
                </div>
                {(
                    auth?.role === 'ADMIN') ? (
                    <>
                        <Link className={'NavLinkDonate'} to={'/donations'}>Donations</Link>
                        <div className={'NavAccountDropdown'}>
                            <button className={'account-dropdown-button'}
                                    onClick={showAccountDropdown}>{auth?.email}<FontAwesomeIcon
                                className={'dropdown-button-icon'} icon={faCaretDown}/></button>
                            <div className={'account-dropdown-content'} id={'accountDropdown'}>
                                <Link to={'/myAccount'}>My Account</Link>
                                <hr/>
                                <a onClick={logOut}>Log Out</a>
                            </div>
                        </div>
                    </>
                ) : (
                    auth?.role === 'CUST') ? (
                    <>
                        <Link className={'NavLinkDonate'} to={'/donateCust'}>Donate</Link>
                        <div className={'NavAccountDropdown'}>
                            <button className={'account-dropdown-button'}
                                    onClick={showAccountDropdown}>{auth?.email}<FontAwesomeIcon
                                className={'dropdown-button-icon'} icon={faCaretDown}/></button>
                            <div className={'account-dropdown-content'} id={'accountDropdown'}>
                                <Link to={'/myAccount'}>My Account</Link>
                                <hr/>
                                <Link to={'/myPets'}>My Pets</Link>
                                <hr/>
                                <Link to={'/myDonations'}>My Donations</Link>
                                <hr/>
                                <a onClick={logOut}>Log Out</a>
                            </div>
                        </div>
                    </>
                ) : (
                    <>
                        <Link className={'NavLinkDonate'} to={'/donate'}>Donate</Link>
                        <Link className={'NavLinkLogIn'} to={'/logIn'}>Log In</Link>
                    </>
                )}
                {auth?.role &&
                    <Notification/>
                }
            </div>
        </>
    );
}

export default Header;