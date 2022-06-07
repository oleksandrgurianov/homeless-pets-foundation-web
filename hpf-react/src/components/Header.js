import React from 'react'
import { Link, Routes, Route } from "react-router-dom"
import logo from "../images/logo.png";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown } from '@fortawesome/free-solid-svg-icons'
import HomePage from "../pages/HomePage";
import PetsPage from "../pages/PetsPage";
import DonatePage from "../pages/DonatePage";

function Header() {
    function showContent() {
        document.getElementById("petsDropdown").classList.toggle("show");
    }

    window.onclick = function(e) {
        if (!e.target.matches('.pets-dropdown-button')) {
            let petsDropdown = document.getElementById("petsDropdown");

            if (petsDropdown != null) {
                if (petsDropdown.classList.contains('show')) {
                    petsDropdown.classList.remove('show');
                }
            }
        }
    }

    return (
        <>
            <div className="NavBar">
                <Link className="NavLogo" to="/">
                    <img src={logo} alt={"homeless pets foundation logo"}/>
                </Link>
                <Link className='NavLink' to="/">Home</Link>
                <div className="NavPetsDropdown">
                    <button className="pets-dropdown-button" onClick={showContent}>Pets<FontAwesomeIcon className="dropdown-icon" icon={faCaretDown} /></button>
                    <div className="pets-dropdown-content" id="petsDropdown">
                        <Link to="/pets/dogs">Dogs</Link>
                        <hr/>
                        <Link to="/pets/cats">Cats</Link>
                        <hr/>
                        <Link to="/pets/rabbits">Rabbits</Link>
                        <hr/>
                        <Link to="/pets/rats">Rats</Link>
                        <hr/>
                        <Link to="/pets/parrots">Parrots</Link>
                    </div>
                </div>
                <Link className='NavLinkDonate' to="/donate">Donate</Link>
                <div className='NavButton'>
                    <button className='log-in-button'>Log In</button>
                </div>
            </div>
            <div className="Body">
                <Routes>
                    {/*<Route path="/" element={<HomePage/>} />*/}
                    <Route path="/pets/:type" element={<PetsPage/>} />
                    <Route path="/donate" element={<DonatePage/>} />
                </Routes>
            </div>
        </>
    );
}

export default Header;