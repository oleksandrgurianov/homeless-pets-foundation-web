import React, {useState, useEffect} from 'react'
import axios from 'axios'
import '../../styles/All/HomePage.css'
import {Link} from 'react-router-dom'
import dog from '../../images/dog.png'
import cat from '../../images/cat.png'
import rabbit from '../../images/rabbit.png'
import rat from '../../images/rat.png'
import parrot from '../../images/parrot.png'
import pawPrints from '../../images/paw-prints.png'
import manRaisingHand from '../../images/man-raising-hand.png'
import moneyWithWings from '../../images/money-with-wings.png'
import loading from "../../images/loading.gif";

const HomePage = () => {
    const [analytics, setAnalytics] = useState([]);

    const getAnalytics = () => {
        axios.get(`http://localhost:8080/analytics`)
            .then(res => {
                setAnalytics(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(() => {
        getAnalytics();
    }, []);

    return (
        <>
            {analytics.length !== 0 ? (
                <div className={'Analytics'}>
                    <div className={'analytics-card'}>
                        <p className={'card-title'}>PETS ADOPTED</p>
                        <p className={'card-total'}>{analytics.petsAdoptedTotal}<img src={pawPrints}
                                                                                     alt={'guide dog emoji'}/></p>
                    </div>
                    <div className={'analytics-card'}>
                        <p className={'card-title'}>CUSTOMERS SATISFIED</p>
                        <p className={'card-total'}>{analytics.customersSatisfiedTotal}<img src={manRaisingHand}
                                                                                            alt={'man raising hand emoji'}/>
                        </p>
                    </div>
                    <div className={'analytics-card'}>
                        <p className={'card-title'}>DONATIONS RECEIVED</p>
                        <p className={'card-total'}>&euro;{analytics.donationsReceivedTotal}<img src={moneyWithWings}
                                                                                                 alt={'money with wings emoji'}/>
                        </p>
                    </div>
                </div>
            ) : (
                <img className={"Loading"} src={loading} alt={'loading gif'}/>
            )}
            <hr className={'AnalyticsLine'}/>
            <div className={'Categories'}>
                <h1>Categories</h1>
                <div className={'categories-list'}>
                    <Link className={'categories-card'} to={'/pets/categories/dogs'}><img src={dog} alt={'dog emoji'}/>Dogs</Link>
                    <Link className={'categories-card'} to={'/pets/categories/cats'}><img src={cat} alt={'cat emoji'}/>Cats</Link>
                    <Link className={'categories-card'} to={'/pets/categories/rabbits'}><img src={rabbit} alt={'rabbit emoji'}/>Rabbits</Link>
                    <Link className={'categories-card'} to={'/pets/categories/rats'}><img src={rat} alt={'rat emoji'}/>Rats</Link>
                    <Link className={'categories-card'} to={'/pets/categories/parrots'}><img src={parrot} alt={'parrot emoji'}/>Parrots</Link>
                </div>
            </div>
        </>
    )
}

export default HomePage