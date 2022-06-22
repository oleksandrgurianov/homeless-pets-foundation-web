import React from 'react';
import "../../styles/All/NotFoundPage.css";
import notFound from "../../images/404.gif";
import {Link} from "react-router-dom";

const NotFoundPage = () => {
    return (
        <div className="NotFound">
            <img src={notFound} alt={"not found gif"}/>
            <p>Sorry, we couldn't find this page. But don't worry, you can find plenty of other things on our <Link
                className="not-found-link" to="/">home page</Link>.</p>
        </div>
    )
}

export default NotFoundPage