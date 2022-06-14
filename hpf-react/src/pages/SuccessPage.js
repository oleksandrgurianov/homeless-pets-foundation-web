import React, {useEffect} from 'react';
import {useNavigate} from "react-router-dom";
import "../styles/SuccessPage.css";
import success from "../images/success.gif";

const SuccessPage = () => {
    let navigate = useNavigate();

    const runTimeout = () => {
        const redirect = () => {
            navigate('/');
        }

        setTimeout(redirect, 5000);
    }

    useEffect(() => {
        runTimeout();
    }, []);

    return (
        <div className="Success">
            <img src={success} alt={"success gif"}/>
            <h1>Success</h1>
        </div>
    )
}

export default SuccessPage