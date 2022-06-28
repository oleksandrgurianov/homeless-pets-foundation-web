import React, {useEffect, useState} from 'react'
import {useNavigate} from 'react-router-dom'
import '../../styles/Administrator/AddPetPage.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCaretDown, faCirclePlus, faPen} from '@fortawesome/free-solid-svg-icons'
import {storage} from '../../services/firebase'
import {getDownloadURL, ref, uploadBytes} from 'firebase/storage'
import {v4} from 'uuid'
import axios from 'axios'

const AddPetPage = () => {
    const [petPictures, setPetPictures] = useState([]);

    const [previewPetPictures, setPreviewPetPictures] = useState([]);

    const [icon, setIcon] = useState();

    const [previewIcon, setPreviewIcon] = useState('');

    const [type, setType] = useState('DOGS');

    const [name, setName] = useState('');

    const [breed, setBreed] = useState('');

    const [ageCategory, setAgeCategory] = useState('');

    const [gender, setGender] = useState('');

    const [size, setSize] = useState('');

    const [color, setColor] = useState('');

    const [description, setDescription] = useState('');

    const [adoptionFee, setAdoptionFee] = useState('');

    const navigate = useNavigate();

    const config = {
        headers: {Authorization: `Bearer ${localStorage.getItem('token')}`}
    };

    let start = 0;

    const addIcon = (e) => {
        if (!e.target.files || e.target.files.length === 0) {
            return;
        }

        setIcon(e.target.files[0]);
    }

    useEffect(() => {
        if (!icon) {
            return;
        }

        const objectUrl = URL.createObjectURL(icon);
        setPreviewIcon(objectUrl);
    }, [icon]);

    const addPetPicture = (e) => {
        if (petPictures.length < 5) {
            const tempArr = [].concat(petPictures);

            [...e.target.files].forEach(file => {
                let obj = {
                    id: tempArr.length + 1,
                    value: file
                }

                tempArr.push(obj);
            });

            setPetPictures(tempArr);
        } else {
            alert('The maximum number of pet pictures have been added.')
        }
    }

    const removePetPicture = (e) => {
        const tempArr = [].concat(petPictures);

        for (let i = 0; i < tempArr.length; i++) {
            if (tempArr[i].id.toString() === e.target.value.toString()) {
                tempArr.splice(i, 1);
                setPetPictures(tempArr);
                return;
            }
        }
    }

    useEffect(() => {
        const tempArr = [];

        petPictures.forEach(file => {
            let obj = {
                id: file.id,
                value: URL.createObjectURL(file.value)
            }

            tempArr.push(obj);
        });

        setPreviewPetPictures(tempArr);
    }, [petPictures]);


    const handleAdoptionFeeChange = (e) => {
        start = e.target.selectionStart;
        let val = e.target.value;
        val = val.replace(/([^0-9.]+)/, '');
        val = val.replace(/^([0.])/, '');
        const match = /(\d{0,7})[^.]*((?:\.\d{0,2})?)/g.exec(val);
        const value = match[1] + match[2];
        e.target.value = value;
        setAdoptionFee(value);

        if (val.length > 0) {
            e.target.value = Number(value).toFixed(2);
            e.target.setSelectionRange(start, start);
            setAdoptionFee(Number(value).toFixed(2));
        }
    }

    const addPet = (e) => {
        e.preventDefault();

        if (!icon) {
            alert('Please add an Icon.')
            return;
        }

        if (!petPictures.length) {
            alert('Please add at least one Pet Picture.')
            return;
        }

        if (!type || !name || !breed) {
            return;
        }

        const iconRef = ref(storage, `icons/${icon.name + v4()}`);

        uploadBytes(iconRef, icon).then(() => {
            getDownloadURL(iconRef).then(url => {
                let pet = {
                    'icon': url,
                    'type': type,
                    'name': name,
                    'breed': breed,
                    'ageCategory': ageCategory,
                    'gender': gender,
                    'size': size,
                    'color': color,
                    'description': description,
                    'adoptionFee': adoptionFee
                };

                axios.post(`http://localhost:8080/pets`, pet, config)
                    .then(res => {
                        console.log(res.data);

                        petPictures.forEach(petPicture => {
                            const petPictureRef = ref(storage, `petPictures/${petPicture.value.name + v4()}`);

                            uploadBytes(petPictureRef, petPicture.value).then(() => {
                                getDownloadURL(petPictureRef).then(url => {
                                    let petPictureObj = {
                                        'petId': res.data.petId,
                                        'picture': url
                                    }

                                    axios.post(`http://localhost:8080/pet_pictures`, petPictureObj, config)
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
                                });
                            })
                        });

                        alert("This pet has been successfully added.");
                        navigate(`/pets/categories/${pet.type}`);
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
            });
        });
    }

    const cancelAddition = () => {
        setPetPictures([]);
        setIcon(undefined);
        setPreviewIcon('');
        setType('DOGS');
        setName('');
        setBreed('');
        setAgeCategory('');
        setGender('');
        setSize('');
        setColor('');
        setDescription('');
        setAdoptionFee('');
    }

    return (
        <>
            <form className={'AddPetForm'}>
                <div className={'Header'}>
                    <h1>Add Pet</h1>
                    <button className={'header-done'} onClick={addPet}>Done</button>
                    <button className={'header-cancel'} onClick={cancelAddition}>Cancel</button>
                </div>
                <hr className={'HeaderLine'}/>
                <div className={'form-icon'}>
                    <div className={'icon-controls'}>
                        <h2>Icon</h2>
                        <label htmlFor="icon-add"><FontAwesomeIcon className="edit-icon" icon={faPen}/></label>
                        <input id={"icon-add"} type="file" accept="image/jpeg, image/png"
                               onChange={addIcon} required/>
                    </div>
                    {previewIcon &&
                        <img src={previewIcon} alt={"pet icon"}/>
                    }
                </div>
                <hr className={'FormLine'}/>
                <div className={'form-pictures'}>
                    <div className={'pictures-controls'}>
                        <h2>Pictures</h2>
                        <label htmlFor="pictures-add"><FontAwesomeIcon className="add-icon"
                                                                       icon={faCirclePlus}/></label>
                        <input id={"pictures-add"} type="file" accept="image/jpeg, image/png" onChange={addPetPicture}
                               required/>
                    </div>
                    <div className={'pictures-add-list'}>
                        {
                            previewPetPictures.map((petPicture) => (
                                <div className={'add-list-item'}>
                                    <img src={petPicture.value} key={petPicture.id} alt={"pet picture"}/>
                                    <button className={"image-delete"} type="button" value={petPicture.id}
                                            onClick={removePetPicture}>-
                                    </button>
                                </div>
                            ))}
                    </div>
                </div>
                <hr className={'FormLine'}/>
                <div className={'form-details'}>
                    <div className={'details-block'}>
                        <div className={'block-dropdown'}>
                            <p>Category</p>
                            <div className={'dropdown-menu'}>
                                <select value={type} onChange={e => setType(e.target.value)}>
                                    <option value={'DOGS'}>Dogs</option>
                                    <option value={'CATS'}>Cats</option>
                                    <option value={'RABBITS'}>Rabbits</option>
                                    <option value={'RATS'}>Rats</option>
                                    <option value={'PARROTS'}>Parrots</option>
                                </select>
                                <FontAwesomeIcon className={'dropdown-icon'} icon={faCaretDown}/>
                            </div>
                        </div>
                        <input
                            type={'text'}
                            name={'name'}
                            placeholder={'Name *'}
                            value={name}
                            onChange={e => setName(e.target.value)}
                            required
                        />
                        <input
                            type={'text'}
                            name={'breed'}
                            placeholder={'Breed *'}
                            value={breed}
                            onChange={e => setBreed(e.target.value)}
                            required
                        />
                        <input
                            type={'text'}
                            name={'ageCategory'}
                            placeholder={'Age Category'}
                            value={ageCategory}
                            onChange={e => setAgeCategory(e.target.value)}
                        />
                    </div>
                    <div className={'details-block'}>
                        <div className={'block-dropdown'}>
                            <p>Gender</p>
                            <div className={'dropdown-menu'}>
                                <select value={gender} onChange={e => setGender(e.target.value)}>
                                    <option value={''}>&#8212;</option>
                                    <option value={'Male'}>Male</option>
                                    <option value={'Female'}>Female</option>
                                </select>
                                <FontAwesomeIcon className={'dropdown-icon'} icon={faCaretDown}/>
                            </div>
                        </div>
                        <input
                            type={'text'}
                            name={'size'}
                            placeholder={'Size'}
                            value={size}
                            onChange={e => setSize(e.target.value)}
                        />
                        <input
                            type={'text'}
                            name={'color'}
                            placeholder={'Color'}
                            value={color}
                            onChange={e => setColor(e.target.value)}
                        />
                        <input
                            type={'text'}
                            name={'adoptionFee'}
                            placeholder={'Adoption Fee'}
                            value={adoptionFee}
                            onChange={handleAdoptionFeeChange}
                        />
                    </div>
                </div>
                <hr className={'FormLine'}/>
                <div className={'form-description'}>
                    <textarea
                        name={'description'}
                        placeholder={'Description'}
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                    />
                </div>
            </form>
        </>
    );
}

export default AddPetPage