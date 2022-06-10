import React, { useState, useEffect } from 'react'
import { useStateWithCallbackLazy } from 'use-state-with-callback'
import axios from 'axios'
import '../styles/PetsPage.css'
import { Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faCaretDown} from '@fortawesome/free-solid-svg-icons'
import loading from "../images/loading.gif"

const PetsPage = () => {
    const [pets, setPets] = useState([]);

    const [breed, setBreed] = useStateWithCallbackLazy('');

    const [sort, setSort] = useStateWithCallbackLazy('');

    const getPets = () => {
        const pathname = new URL(window.location.href).pathname;

        const pathnameArray = pathname.split('/');

        const type = pathnameArray[2];

        axios.get(`http://localhost:8080/pets?type=${type}&breed=${breed}&sort=${sort}`)
            .then(res => {
                setPets(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(() => {
        getPets();
    }, [pets]);

    function searchPets(e) {
        setBreed(e, () => {
            getPets();
        })
    }

    function sortPets(e) {
        setSort(e, () => {
            getPets();
        })
    }

    return (
        <>
            <div className={'Header'}>
                <h1>Pets</h1>
                <input className={'header-search'} type={'text'} placeholder={'Search'} onChange={e => searchPets(e.target.value)}/>
                <div className={'header-dropdown'}>
                    <select onChange={e => sortPets(e.target.value)}>
                        <option value={'nameAsc'}>Name &uarr;</option>
                        <option value={'nameDesc'}>Name &darr;</option>
                        <option value={'adoptionFeeAsc'}>Adoption Fee &uarr;</option>
                        <option value={'adoptionFeeDesc'}>Adoption Fee &darr;</option>
                    </select>
                    <FontAwesomeIcon className={'dropdown-icon'} icon={faCaretDown}/>
                </div>
            </div>
            <hr className={'HeaderLine'}/>
            {pets.pets ? (
                <div className={'Pets'}>
                    {pets.pets.map((pet) => (
                        <Link className='pets-card' to={`/*`}>
                            <img src={pet.icon}/>
                            <p className={'card-name'}>{pet.name}</p>
                            <p className={'card-breed'}>{pet.breed}</p>
                        </Link>
                    ))}
                </div>
            ) : (
                <img className={"Loading"} src={loading}/>
            )}
        </>
    )
}

export default PetsPage