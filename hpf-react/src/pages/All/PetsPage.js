import React, {useState, useEffect} from 'react'
import {useLocation, Link} from 'react-router-dom'
import axios from 'axios'
import '../../styles/All/PetsPage.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCirclePlus, faCaretDown} from '@fortawesome/free-solid-svg-icons'
import loading from "../../images/loading.gif"

const PetsPage = () => {
    const location = useLocation();

    const [pets, setPets] = useState([]);

    const [search, setSearch] = useState('');

    const [sort, setSort] = useState('');

    const [filteredPets, setFilteredPets] = useState([]);

    const getPets = () => {
        const pathname = new URL(window.location.href).pathname;

        axios.get(`http://localhost:8080${pathname}`)
            .then(res => {
                setPets(res.data.pets);
                console.log(res.data.pets);
            })
            .catch(err => {
                console.log(err);
            });
    }

    useEffect(() => {
        getPets();
    }, [location]);

    useEffect(() => {
        const filteredResults = [].concat(pets).filter(pet => {
            if (search === '') {
                return pet;
            } else {
                return pet.breed.toLowerCase().includes(search.toLowerCase());
            }
        }).sort((a, b) => {
            if (sort === 'nameAsc') {
                return a.name > b.name ? 1 : -1;
            } else if (sort === 'nameDesc') {
                return a.name > b.name ? -1 : 1;
            } else if (sort === 'adoptionFeeAsc') {
                return a.adoptionFee > b.adoptionFee ? 1 : -1;
            } else if (sort === 'adoptionFeeDesc') {
                return a.adoptionFee > b.adoptionFee ? -1 : 1;
            }
        });

        setFilteredPets(filteredResults);
    }, [pets, search, sort]);

    return (
        <>
            <div className={'Header'}>
                <h1>Pets</h1>
                {(localStorage.getItem('role') === 'ADMIN') &&
                    <Link to='/pets/addPet'><FontAwesomeIcon className='add-icon' icon={faCirclePlus}/></Link>
                }
                <input className={'header-search'} type={'text'} placeholder={'Search'}
                       value={search} onChange={e => setSearch(e.target.value)}/>
                <div className={'header-dropdown'}>
                    <select onChange={e => setSort(e.target.value)}>
                        <option value={'nameAsc'}>Name &uarr;</option>
                        <option value={'nameDesc'}>Name &darr;</option>
                        <option value={'adoptionFeeAsc'}>Adoption Fee &uarr;</option>
                        <option value={'adoptionFeeDesc'}>Adoption Fee &darr;</option>
                    </select>
                    <FontAwesomeIcon className={'dropdown-icon'} icon={faCaretDown}/>
                </div>
            </div>
            <hr className={'HeaderLine'}/>
            {pets.length ? (
                (
                    filteredPets.length ? (
                        <div className={'Pets'}>
                            {
                                filteredPets.map((pet) => (
                                    <Link className='pets-card'
                                          to={`${pet.id}`} key={pet.id}>
                                        <img src={pet.icon} alt={'pet icon'}/>
                                        <p className={'card-name'}>{pet.name}</p>
                                        <p className={'card-breed'}>{pet.breed}</p>
                                    </Link>
                                ))}
                        </div>
                    ) : (
                        <div className={'NoMatches'}>
                            <h2>Sorry, no matches were found.</h2>
                            <h2>Try a new search or use our suggestions.</h2>
                        </div>
                    )
                )) : (
                <img className={"Loading"} src={loading} alt={'loading gif'}/>
            )}
        </>
    )
}

export default PetsPage