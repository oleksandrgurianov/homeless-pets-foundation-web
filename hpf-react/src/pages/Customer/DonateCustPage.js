import React, {useState, useEffect, useRef} from 'react'
import {useNavigate} from "react-router-dom";
import axios from "axios";
import '../../styles/All/DonatePage.css'
import Cards from 'react-credit-cards'
import 'react-credit-cards/es/styles-compiled.css'
import {
    formatCreditCardNumber,
    formatCVC,
    formatExpirationDate
} from '../../services/CardService'

const DonateCustPage = () => {
    const [customer, setCustomer] = useState({});

    const [cardNumber, setCardNumber] = useState('');

    const [fullName, setFullName] = useState('');

    const [expirationDate, setExpirationDate] = useState('');

    const [cvv, setCvv] = useState('');

    const [focus, setFocus] = useState('');

    const [saveBankDetails, setSaveBankDetails] = useState(false);

    const [amount, setAmount] = useState('');

    const [description, setDescription] = useState('');

    const ref = useRef(null);

    const config = {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
    };

    let navigate = useNavigate();

    let start = 0;

    const getCustomer = () => {
        axios.get(`http://localhost:8080/customers/${localStorage.getItem('userId')}`, config)
            .then(res => {
                setCustomer(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            });
    }

    useEffect(() => {
        getCustomer();
    }, []);

    useEffect(() => {
        ref.current.focus();
    }, []);

    const handleNumberChange = (e) => {
        const result = formatCreditCardNumber(e.target.value);
        setCardNumber(result);

        if(document.getElementById('useCheckbox') !== null) {
            document.getElementById('useCheckbox').checked = false;
        }
    };

    const handleFullNameChange = (e) => {
        setFullName(e.target.value);

        if(document.getElementById('useCheckbox') !== null) {
            document.getElementById('useCheckbox').checked = false;
        }
    };

    const handleExpiryChange = (e) => {
        const result = formatExpirationDate(e.target.value);
        setExpirationDate(result);

        if(document.getElementById('useCheckbox') !== null) {
            document.getElementById('useCheckbox').checked = false;
        }
    };

    const handleCvcChange = (e) => {
        const result = formatCVC(e.target.value);
        setCvv(result);

        if(document.getElementById('useCheckbox') !== null) {
            document.getElementById('useCheckbox').checked = false;
        }
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

    const handleUseCheckboxChange = () => {
        const useCheckbox = document.getElementById('useCheckbox');

        if (useCheckbox.checked) {
            setCardNumber(customer.cardNumber);
            setFullName(customer.user.fullName);
            setExpirationDate(customer.expirationDate);
            setCvv(customer.cvv);
        } else {
            setCardNumber('');
            setFullName('');
            setExpirationDate('');
            setCvv('');
        }
    }

    const handleSaveCheckboxChange = () => {
        const saveCheckbox = document.getElementById('saveCheckbox');

        if (saveCheckbox.checked) {
            setSaveBankDetails(true);
        } else {
            setSaveBankDetails(false);
        }
    }

    const sendDonation = () => {
        let donation = {
            'customerId': customer.id,
            'amount': amount,
            'description': description
        };

        if (donation.amount > 0) {
            if (saveBankDetails) {
                let bankDetails = {
                    'cardNumber': cardNumber,
                    'expirationDate': expirationDate,
                    'cvv': cvv
                }

                axios.put(`http://localhost:8080/customers/${localStorage.getItem('userId')}/bankDetails`, bankDetails, config)
                    .then(res => {
                        setCustomer(res.data);
                        console.log(res.data);
                    })
                    .catch(err => {
                        console.log(err);
                    });
            }

            axios.post(`http://localhost:8080/donations`, donation, config)
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

        if(document.getElementById('useCheckbox') !== null) {
            document.getElementById('useCheckbox').checked = false;
        } else {
            document.getElementById('saveCheckbox').checked = false;
        }

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
                            onChange={handleFullNameChange}
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
                        {(customer.cardNumber !== null) ? (
                            <label className={'details-checkbox'}>
                                <input
                                    type="checkbox"
                                    name='useCheckbox'
                                    id='useCheckbox'
                                    onChange={handleUseCheckboxChange}
                                />
                                Use saved payment method
                            </label>
                        ) : (
                            <label className={'details-checkbox'}>
                                <input
                                    type="checkbox"
                                    name='saveCheckbox'
                                    id='saveCheckbox'
                                    onChange={handleSaveCheckboxChange}
                                />
                                Save payment method
                            </label>
                        )}
                    </div>
                </div>
                <div className={'form-amount'}>
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
                        className={'donate-cust-textarea'}
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

export default DonateCustPage