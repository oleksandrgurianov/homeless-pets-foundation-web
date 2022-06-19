import React, {useState, useEffect, useRef} from 'react'
import {useNavigate} from "react-router-dom";
import axios from "axios";
import '../styles/DonatePage.css'
import Cards from 'react-credit-cards'
import 'react-credit-cards/es/styles-compiled.css'
import {
    formatCreditCardNumber,
    formatCVC,
    formatExpirationDate
} from '../services/DonationService'

const DonatePage = () => {
    const [cardNumber, setCardNumber] = useState('');

    const [fullName, setFullName] = useState('');

    const [expirationDate, setExpirationDate] = useState('');

    const [cvv, setCvv] = useState('');

    const [focus, setFocus] = useState('');

    const [email, setEmail] = useState('')

    const [amount, setAmount] = useState('');

    const [description, setDescription] = useState('')

    const ref = useRef(null);

    let navigate = useNavigate();

    let start = 0;

    useEffect(() => {
        ref.current.focus();
    }, []);

    const handleNumberChange = (e) => {
        const result = formatCreditCardNumber(e.target.value);
        setCardNumber(result);
    };

    const handleExpiryChange = (e) => {
        const result = formatExpirationDate(e.target.value);
        setExpirationDate(result);
    };

    const handleCvcChange = (e) => {
        const result = formatCVC(e.target.value);
        setCvv(result);
    };

    const handleAmountChange = (e) => {
        start = e.target.selectionStart;
        let val = e.target.value;
        val = val.replace(/([^0-9.]+)/, '');
        val = val.replace(/^([0.])/, '');
        const match = /(\d{0,7})[^.]*((?:\.\d{0,2})?)/g.exec(val);
        const value = match[1] + match[2];
        e.target.value = value;
        setAmount(value);

        if (val.length > 0) {
            e.target.value = Number(value).toFixed(2);
            e.target.setSelectionRange(start, start);
            setAmount(Number(value).toFixed(2));
        }
    }

    const sendDonation = () => {
        let donation = {
            'amount': amount,
            'description': description
        };

        if (donation.amount > 0) {
            axios.post(`http://localhost:8080/donations`, donation)
                .then(res => {
                    console.log(res.data);

                })
                .catch(err => {
                    console.log(err);
                });

            alert("Thank you!\nYour donation has been received.\nWe will update you shortly.");
            navigate('/');
        } else {
            alert("Please fill out the Amount field.")
        }
    }

    const cancelDonation = () => {
        setCardNumber('');
        setFullName('');
        setExpirationDate('');
        setCvv('');
        setEmail('');
        setAmount('');
        setDescription('');
    }

    return (
        <>
            <h1>Donate</h1>
            <hr className={'DonateLine'}/>
            <form className={'DonateForm'}>
                <div className={'form-card'}>
                    <Cards
                        number={cardNumber}
                        name={fullName}
                        expiry={expirationDate}
                        cvc={cvv}
                        focused={focus}
                    />
                    <div className={'card-details'}>
                        <input
                            type={'tel'}
                            name={'number'}
                            placeholder={'Card Number *'}
                            value={cardNumber}
                            onChange={handleNumberChange}
                            onFocus={(e) => setFocus(e.target.name)}
                            ref={ref}
                            required
                        />
                        <input
                            type={'text'}
                            name={'name'}
                            placeholder={'Full Name *'}
                            value={fullName}
                            onChange={(e) => setFullName(e.target.value)}
                            onFocus={(e) => setFocus(e.target.name)}
                            required
                        />
                        <div className={'details-footer'}>
                            <input
                                type={'text'}
                                name={'expiry'}
                                placeholder={'MM/YY *'}
                                value={expirationDate}
                                onChange={handleExpiryChange}
                                onFocus={(e) => setFocus(e.target.name)}
                                required
                            />
                            <input
                                type={'tel'}
                                name={'cvc'}
                                placeholder={'CVV *'}
                                value={cvv}
                                onChange={handleCvcChange}
                                onFocus={(e) => setFocus(e.target.name)}
                                required
                            />
                        </div>
                    </div>
                </div>
                <div className={'form-amount'}>
                    <input
                        type={'email'}
                        name={'email'}
                        placeholder={'Email *'}
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        type={'text'}
                        name={'amount'}
                        placeholder={'Amount (in EUR) *'}
                        value={amount}
                        onChange={handleAmountChange}
                        required
                    />
                    <textarea
                        name={'description'}
                        placeholder={'Description'}
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                    <div className={'amount-footer'}>
                        <button className={'footer-send'} onClick={sendDonation}>Send</button>
                        <button className={'footer-cancel'} onClick={cancelDonation}>Cancel</button>
                    </div>
                </div>
            </form>
        </>
    )
}

export default DonatePage