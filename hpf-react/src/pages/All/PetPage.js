import React, {useEffect, useState} from 'react'
import {Link, Navigate, useNavigate} from 'react-router-dom'
import axios from 'axios'
import '../../styles/All/PetPage.css'
import Collapsible from 'react-collapsible'
import Cards from 'react-credit-cards'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faChevronDown} from '@fortawesome/free-solid-svg-icons'
import loading from '../../images/loading.gif'
import {
    formatCreditCardNumber,
    formatCVC,
    formatExpirationDate
} from '../../services/CardService'


const PetPage = () => {
    const [petPictures, setPetPictures] = useState([]);

    const [pet, setPet] = useState({});

    const [customer, setCustomer] = useState({});

    const [street, setStreet] = useState('');

    const [postcode, setPostcode] = useState('');

    const [city, setCity] = useState('');

    const [cardNumber, setCardNumber] = useState('');

    const [fullName, setFullName] = useState('');

    const [expirationDate, setExpirationDate] = useState('');

    const [cvv, setCvv] = useState('');

    const [focus, setFocus] = useState('');

    const [saveAddress, setSaveAddress] = useState(false);

    const [saveBankDetails, setSaveBankDetails] = useState(false);

    const config = {
        headers: {Authorization: `Bearer ${localStorage.getItem('token')}`}
    };

    let navigate = useNavigate();

    const getCustomer = () => {
        if (localStorage.getItem('role') === 'CUST') {
            axios.get(`http://localhost:8080/customers/${localStorage.getItem('userId')}`, config)
                .then(res => {
                    setCustomer(res.data);
                    console.log(res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }

    useEffect(() => {
        getCustomer();
    }, []);

    const getPetPictures = () => {
        const pathname = new URL(window.location.href).pathname;

        const pathArray = pathname.split('/');

        const petId = pathArray[4];

        axios.get(`http://localhost:8080/pet_pictures/${petId}`, config)
            .then(res => {
                setPetPictures(res.data.petPictures);
                console.log(res.data.petPictures);
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(() => {
        getPetPictures();
    }, []);

    const getPet = () => {
        const pathname = new URL(window.location.href).pathname;

        const pathArray = pathname.split('/');

        const petId = pathArray[4];

        axios.get(`http://localhost:8080/pets/${petId}`, config)
            .then(res => {
                setPet(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            });
    }

    useEffect(() => {
        getPet();
    }, []);

    const deletePet = () => {
        axios.delete(`http://localhost:8080/pets/${pet.id}`, config)
            .then(res => {
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            });

        alert("This pet has been successfully deleted.");
        navigate(`/pets/categories/${pet.type}`);
    }

    const handleStreetChange = (e) => {
        setStreet(e.target.value);

        if (document.getElementById('useAddressCheckbox') !== null) {
            document.getElementById('useAddressCheckbox').checked = false;
        }
    };

    const handlePostcodeChange = (e) => {
        setPostcode(e.target.value);

        if (document.getElementById('useAddressCheckbox') !== null) {
            document.getElementById('useAddressCheckbox').checked = false;
        }
    };

    const handleCityChange = (e) => {
        setCity(e.target.value);

        if (document.getElementById('useAddressCheckbox') !== null) {
            document.getElementById('useAddressCheckbox').checked = false;
        }
    };

    const handleUseAddressCheckboxChange = () => {
        const useCheckbox = document.getElementById('useAddressCheckbox');

        if (useCheckbox.checked) {
            setStreet(customer.street);
            setPostcode(customer.postcode);
            setCity(customer.city);
        } else {
            setStreet('');
            setPostcode('');
            setCity('');
        }
    }

    const handleSaveAddressCheckboxChange = () => {
        const saveCheckbox = document.getElementById('saveAddressCheckbox');

        if (saveCheckbox.checked) {
            setSaveAddress(true);
        } else {
            setSaveAddress(false);
        }
    }

    const handleNumberChange = (e) => {
        const result = formatCreditCardNumber(e.target.value);
        setCardNumber(result);

        if (document.getElementById('useCardCheckbox') !== null) {
            document.getElementById('useCardCheckbox').checked = false;
        }
    };

    const handleFullNameChange = (e) => {
        setFullName(e.target.value);

        if (document.getElementById('useCardCheckbox') !== null) {
            document.getElementById('useCardCheckbox').checked = false;
        }
    };

    const handleExpiryChange = (e) => {
        const result = formatExpirationDate(e.target.value);
        setExpirationDate(result);

        if (document.getElementById('useCardCheckbox') !== null) {
            document.getElementById('useCardCheckbox').checked = false;
        }
    };

    const handleCvcChange = (e) => {
        const result = formatCVC(e.target.value);
        setCvv(result);

        if (document.getElementById('useCardCheckbox') !== null) {
            document.getElementById('useCardCheckbox').checked = false;
        }
    };

    const handleUseCardCheckboxChange = () => {
        const useCheckbox = document.getElementById('useCardCheckbox');

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

    const handleSaveCardCheckboxChange = () => {
        const saveCheckbox = document.getElementById('saveCardCheckbox');

        if (saveCheckbox.checked) {
            setSaveBankDetails(true);
        } else {
            setSaveBankDetails(false);
        }
    }

    const adoptPet = () => {
        let adoption = {
            'customerId': customer.id
        };

        if (saveAddress) {
            let address = {
                'street': street,
                'postcode': postcode,
                'city': city
            }

            axios.put(`http://localhost:8080/customers/${localStorage.getItem('userId')}/address`, address, config)
                .then(res => {
                    setCustomer(res.data);
                    console.log(res.data);
                })
                .catch(err => {
                    if (!err?.response) {
                        alert('There was an error connecting to the HPF server.');
                    } else if (err.response?.status === 400) {
                        alert('Please fill out all the required fields.');
                    } else if (err.response?.status === 401) {
                        alert('You are not authorized');
                    } else {
                        alert('An unknown error occurred.')
                    }

                    console.log(err);
                });
        }

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
                    if (!err?.response) {
                        alert('There was an error connecting to the HPF server.');
                    } else if (err.response?.status === 400) {
                        alert('Please fill out all the required fields.');
                    } else if (err.response?.status === 401) {
                        alert('You are not authorized');
                    } else {
                        alert('An unknown error occurred.')
                    }

                    console.log(err);
                });
        }

        axios.put(`http://localhost:8080/pets/${pet.id}/customer`, adoption, config)
            .then(res => {
                console.log(res.data);
            })
            .catch(err => {
                if (!err?.response) {
                    alert('There was an error connecting to the HPF server.');
                } else if (err.response?.status === 400) {
                    alert('Please fill out all the required fields.');
                } else if (err.response?.status === 401) {
                    alert('You are not authorized');
                } else {
                    alert('An unknown error occurred.')
                }

                console.log(err);
            });

        alert("Thank you!\nYour adoption request has been received.\nWe will update you shortly.");
        navigate('/');
    }

    const cancelAdoption = () => {
        setStreet('');
        setPostcode('');
        setCity('');

        if (document.getElementById('useAddressCheckbox') !== null) {
            document.getElementById('useAddressCheckbox').checked = false;
        } else {
            document.getElementById('saveAddressCheckbox').checked = false;
        }

        if (pet.adoptionFee > 0) {
            setCardNumber('');
            setFullName('');
            setExpirationDate('');
            setCvv('');

            if (document.getElementById('useCardCheckbox') !== null) {
                document.getElementById('useCardCheckbox').checked = false;
            } else {
                document.getElementById('saveCardCheckbox').checked = false;
            }
        }
    }

    return (
        <>
            {localStorage.getItem('role') ? (
                <>
                    {petPictures.length ? (
                        <div className={'PetPictures'}>
                            <ul className='pictures-list'>
                                {petPictures.map((petPicture) => (
                                    <li className='list-item' key={petPicture.id}><img src={petPicture.picture}
                                                                                       alt={'pet image'}/></li>
                                ))}
                            </ul>
                        </div>
                    ) : (
                        <img className={"Loading"} src={loading} alt={'loading gif'}/>
                    )}
                    {(
                        pet.length !== 0) ? (
                        <>
                            <div className={'Header'}>
                                <h1>{pet.name}</h1>
                                {(
                                    localStorage.getItem('role') === 'ADMIN') ? (
                                    <>
                                        <Link className='header-link' to={`updatePet`}>Edit</Link>
                                        <button className='header-button' onClick={deletePet}>Delete</button>
                                    </>
                                ) : (
                                    <></>
                                )}
                            </div>
                            <hr className={'HeaderLine'}/>
                            <div className={'PetDetails'}>
                                <div className={'details-row'}>
                                    <p>{pet.breed}</p>
                                    {pet.ageCategory &&
                                        <>
                                            <p className={'row-separator'}>&bull;</p>
                                            <p>{pet.ageCategory}</p>
                                        </>
                                    }
                                    {pet.gender &&
                                        <>
                                            <p className={'row-separator'}>&bull;</p>
                                            <p>{pet.gender}</p>
                                        </>
                                    }
                                    {pet.size &&
                                        <>
                                            <p className={'row-separator'}>&bull;</p>
                                            <p>{pet.size}</p>
                                        </>
                                    }
                                </div>
                                {pet.color &&
                                    <p className={'details-item'}><b>Color:</b> {pet.color}</p>
                                }
                                {(
                                    pet.adoptionFee > 0) ? (
                                    <p className={'details-item'}><b>Adoption fee: </b> &euro;{pet.adoptionFee}</p>
                                ) : (
                                    <p className={'details-item'}><b>Adoption fee: </b> Free</p>
                                )}
                            </div>
                            {pet.description &&
                                <>
                                    <hr className={'PetDetailsLine'}/>
                                    <div className={'PetDescription'}>
                                        <h2>Description</h2>
                                        <p>{pet.description}</p>
                                    </div>
                                </>
                            }
                            {(
                                    localStorage.getItem('role') === 'CUST') &&
                                <div className={'collapsible-overlay'}>
                                    <hr className={'PetDescriptionLine'}/>
                                    <FontAwesomeIcon className={'overlay-icon'} icon={faChevronDown}/>
                                    <Collapsible trigger={'Adoption'} triggerOpenedClassName={'trigger-opened'}>
                                        <form className={'AdoptionForm'}>
                                            <div className={'adoption-address'}>
                                                <div className={'address-details'}>
                                                    <input
                                                        type={'text'}
                                                        name={'street'}
                                                        placeholder={'Street *'}
                                                        value={street}
                                                        pattern={'^([w.\'/- ]+) (w?[0-9]+[a-zA-Z0-9- ]*)'}
                                                        onChange={handleStreetChange}
                                                        required
                                                    />
                                                    <input
                                                        type={'text'}
                                                        name={'postcode'}
                                                        placeholder={'Postcode *'}
                                                        value={postcode}
                                                        pattern={'^[1-9][0-9]{3} ?(?!sa|sd|ss|SA|SD|SS)[A-Za-z]{2}$'}
                                                        onChange={handlePostcodeChange}
                                                        required
                                                    />
                                                    <input
                                                        type={'text'}
                                                        name={'city'}
                                                        placeholder={'City *'}
                                                        value={city}
                                                        pattern={'^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$'}
                                                        onChange={handleCityChange}
                                                        required
                                                    />
                                                </div>
                                                {(
                                                    customer.street !== null) ? (
                                                    <label className={'details-checkbox'}>
                                                        <input
                                                            type="checkbox"
                                                            name='useAddressCheckbox'
                                                            id='useAddressCheckbox'
                                                            onChange={handleUseAddressCheckboxChange}
                                                        />
                                                        Use saved address
                                                    </label>
                                                ) : (
                                                    <label className={'details-checkbox'}>
                                                        <input
                                                            type="checkbox"
                                                            name='saveAddressCheckbox'
                                                            id='saveAddressCheckbox'
                                                            onChange={handleSaveAddressCheckboxChange}
                                                        />
                                                        Save address
                                                    </label>
                                                )}
                                            </div>
                                            {(
                                                    pet.adoptionFee > 0) &&
                                                <div className={'adoption-card'}>
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
                                                        {(
                                                            customer.cardNumber !== null) ? (
                                                            <label className={'details-checkbox'}>
                                                                <input
                                                                    type="checkbox"
                                                                    name='useCardCheckbox'
                                                                    id='useCardCheckbox'
                                                                    onChange={handleUseCardCheckboxChange}
                                                                />
                                                                Use saved payment method
                                                            </label>
                                                        ) : (
                                                            <label className={'details-checkbox'}>
                                                                <input
                                                                    type="checkbox"
                                                                    name='saveCardCheckbox'
                                                                    id='saveCardCheckbox'
                                                                    onChange={handleSaveCardCheckboxChange}
                                                                />
                                                                Save payment method
                                                            </label>
                                                        )}
                                                    </div>
                                                </div>
                                            }
                                            <div className={'adoption-buttons'}>
                                                <button className={'buttons-adopt'} onClick={adoptPet}>Adopt</button>
                                                <button className={'buttons-cancel'} onClick={cancelAdoption}>Cancel
                                                </button>
                                            </div>
                                        </form>
                                    </Collapsible>
                                </div>
                            }
                        </>
                    ) : (
                        <img className={"Loading"} src={loading} alt={'loading gif'}/>
                    )}
                </>
            ) : (
                <Navigate to={'/logIn'} replace={true}/>
            )}
        </>
    )

}

export default PetPage