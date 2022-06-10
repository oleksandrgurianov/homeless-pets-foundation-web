import React from 'react'
import logo from '../images/logo.png'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebookF, faLinkedinIn, faTwitter, faYoutube  } from '@fortawesome/free-brands-svg-icons'

function Footer() {
    return (
        <footer>
            <div className={'row'}>
                <img src={logo} height={'44px'} alt={'homeless pets foundation logo'}/>
                <div className={'socials'}>
                    <a href={''}>
                        <FontAwesomeIcon className={'socials-icon'} icon={faLinkedinIn} />
                    </a>
                    <a href={''}>
                        <FontAwesomeIcon className={'socials-icon'} icon={faFacebookF} />
                    </a>
                    <a href={''}>
                        <FontAwesomeIcon className={'socials-icon'} icon={faYoutube} />
                    </a>
                    <a href={''}>
                        <FontAwesomeIcon className={'socials-icon'} icon={faTwitter} />
                    </a>
                </div>
                <p className={'copyright'}>Copyright &copy; 2022 Homeless Pets Foundation. All rights reserved.</p>
            </div>
            <div className={'row'}>
                <a href={''}>About Us</a>
                <a href={''}>Terms of Service</a>
                <a href={''}>Mobile Site & Apps</a>
            </div>
            <div className={'row'}>
                <a href={''}>Our Foundation</a>
                <a href={''}>Mobile Apps</a>
                <a href={''}>Press</a>
            </div>
            <div className={'row'}>
                <a href={''}>For Developers</a>
                <a href={''}>Helping Pets</a>
                <a href={''}>Contact Us</a>
            </div>
        </footer>
    )
}

export default Footer;