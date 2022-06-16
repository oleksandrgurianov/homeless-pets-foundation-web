import React from 'react'
import {Link, Routes, Route} from 'react-router-dom'
import logo from '../images/logo.png'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCaretDown, faCircleXmark} from '@fortawesome/free-solid-svg-icons'
import HomePage from '../pages/HomePage'
import PetsPage from '../pages/PetsPage'
import DonatePage from '../pages/DonatePage/DonatePage'
import LogInPage from '../pages/LogInPage'
import NotFoundPage from "../pages/NotFoundPage"

function Header() {
    function showPetsDropdown() {
        document.getElementById('petsDropdown').classList.toggle('show');
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
                <Link className={'NavLinkDonate'} to={'/donate'}>Donate</Link>
                <Link className={'NavLinkLogIn'} to={'/logIn'}>Log In</Link>
            </div>
            <div className={'Body'}>
                <Routes>
                    <Route path={'/'} element={<HomePage/>}/>
                    <Route path={'/pets/categories/:type'} element={<PetsPage/>}/>
                    <Route path={'/donate'} element={<DonatePage/>}/>
                    <Route path={'/logIn'} element={<LogInPage/>}/>
                    <Route path={'/*'} element={<NotFoundPage/>}/>
                </Routes>
            </div>
        </>
    );
}

export default Header;