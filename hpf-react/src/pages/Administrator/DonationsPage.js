import React, {useEffect, useState} from 'react'
import axios from 'axios'
import '../../styles/Administrator/DonationsPage.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCaretDown} from '@fortawesome/free-solid-svg-icons'
import loading from '../../images/loading.gif'
import moneyWithWings from '../../images/money-with-wings.png'
import useAuth from '../../hooks/useAuth'

const DonationsPage = () => {
    const {auth} = useAuth();

    const [donations, setDonations] = useState([]);

    const [sort, setSort] = useState('');

    const [filteredDonations, setFilteredDonations] = useState([]);

    const config = {
        headers: {Authorization: `Bearer ${auth?.token}`}
    };

    const getDonations = () => {
        const pathname = new URL(window.location.href).pathname;

        axios.get(`http://localhost:8080${pathname}`, config)
            .then(res => {
                setDonations(res.data.donations);
                console.log(res.data.donations);
            })
            .catch(err => {
                console.log(err);
            });
    }

    useEffect(() => {
        getDonations();
    }, []);

    useEffect(() => {
        const filteredResults = [].concat(donations).sort((a, b) => {
            if (sort === 'dateOfReceiptAsc') {
                return a.dateOfReceipt > b.dateOfReceipt ? 1 : -1;
            } else if (sort === 'dateOfReceiptDesc') {
                return a.dateOfReceipt > b.dateOfReceipt ? -1 : 1;
            } else if (sort === 'amountAsc') {
                return a.amount > b.amount ? 1 : -1;
            } else if (sort === 'amountDesc') {
                return a.amount > b.amount ? -1 : 1;
            }
        });

        setFilteredDonations(filteredResults);
    }, [donations, sort]);

    return (
        <>
            <div className={'Header'}>
                <h1>Donations</h1>
                <div className={'header-dropdown'}>
                    <select defaultValue={'dateOfReceiptDesc'} onChange={e => setSort(e.target.value)}>
                        <option value={'dateOfReceiptAsc'}>Date of receipt &uarr;</option>
                        <option value={'dateOfReceiptDesc'}>Date of receipt &darr;</option>
                        <option value={'amountAsc'}>Amount &uarr;</option>
                        <option value={'amountDesc'}>Amount &darr;</option>
                    </select>
                    <FontAwesomeIcon className={'dropdown-icon'} icon={faCaretDown}/>
                </div>
            </div>
            <hr className={'DonationsLine'}/>
            {donations.length ? (
                <div className={'Donations'}>
                    {filteredDonations.map((donation) => (
                        <div className='donation-card' key={donation.id}>
                            <div>
                                {donation.customer ? (
                                    <p className={'card-full-name'}>{donation.customer.user.fullName}</p>
                                ) : (
                                    <p className={'card-full-name'}>Anonymous</p>
                                )}
                                {donation.description ? (
                                    <p className={'card-description'}>{donation.description}</p>
                                ) : (
                                    <p className={'card-description'}>&#8212;</p>
                                )}
                            </div>
                            <div>
                                <p className={'card-date-of-receipt'}>{donation.dateOfReceipt}</p>
                                <p className={'card-amount'}>&euro;{donation.amount}<img src={moneyWithWings}
                                                                                         alt={'money with wings emoji'}/>
                                </p>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <img className={"Loading"} src={loading} alt={'loading gif'}/>
            )}
        </>
    )
}

export default DonationsPage