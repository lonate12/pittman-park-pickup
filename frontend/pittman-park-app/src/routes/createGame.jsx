import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import dayjs from 'dayjs';
import axios from 'axios';
import Modal from 'react-modal';
import { RotatingLines } from 'react-loader-spinner';

import { baseApiUrl, getUser, axiosConfigObj, modalStyles } from '../helperModule';


export default function CreateGame() {
    const [formData, setFormData] = useState({gameTime: "", pw: "", location: "Pittman Park"});
    const [modalIsOpen, setIsOpen] = useState(false);

    const navigate = useNavigate();
    const currentDate = dayjs().format("YYYY-MM-DDTHH:mm");

    function openModal() {
        setIsOpen(true);
    }

    function closeModal() {
        setIsOpen(false);
    }

    function togglePassword(){
        const pwInput = document.getElementById("pw");
        if (pwInput.type === "password") {
            pwInput.type = "text";
        } else {
            pwInput.type = "password";
        }
    }

    function handleChange(e) {
        const {name, value} = e.target;
        setFormData((prevFormData) => {
            return {...prevFormData, [name]: value};
        });
    }

    function handleSubmit(e) {
        e.preventDefault();
        if (e.nativeEvent.submitter.name === "cancelButton") {
            navigate("/");
            return false;
        }

        const now = dayjs();
        const desiredGameTime = dayjs(formData.gameTime);

        // Validate gametime isn't in the past
        if (desiredGameTime.isBefore(now)) {
            alert(`Game time cannot be in the past. You are trying to set the game for ${desiredGameTime.format('MM/DD/YYYY @ HH:mm')}. Right now, it is ${now.format('MM/DD/YYYY, [around] HH:mm')}.`)
            return false;
        }
        // Create request obj
        let gameRequestObj = {
            gameTime: dayjs(formData.gameTime).valueOf().toString(),
            pw: formData.pw,
            userId: getUser().userId
        };

        console.log("Submitting new game request...");
        openModal();
        setTimeout(() => {
            closeModal();
        }, 1000000);
        axios.post(
            baseApiUrl + "/games",
            gameRequestObj,
            axiosConfigObj
        ).then((res) => {
            console.log("Game created successfully!");
            console.log(res);
            navigate("/");
        }).catch(e => {
            alert("Something went wrong! The game wasn't created.");
            return false;
        });
    }

    return (
        <>
        <div className="row">
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                ariaHideApp={false}
                style={modalStyles}
            >
                <div className="text-center">
                    <h2>Creating the game...give us a moment will you?!</h2>
                    <div className="text-center mt-5">
                        <RotatingLines strokeColor="grey"/>
                    </div>
                </div>
            </Modal>
            <h1 className="welcome-header text-center col-12 col-md-4 offset-md-4"><Link to="/">Pittman Park Pickup</Link></h1>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="location" className="form-label">Location</label>
                    <input type="text" 
                    className="form-control" 
                    id="location" 
                    name="location" 
                    disabled 
                    value={formData.location} 
                    onChange={handleChange}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="gameTime" className="form-label">Game Time</label>
                    <input type="datetime-local" 
                        className="form-control" 
                        id="gameTime" 
                        name="gameTime"
                        min={currentDate} 
                        value={formData.gameTime} 
                        onChange={handleChange}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="pw" className="form-label">Admin Password</label>
                    <input type="password" className="form-control" id="pw" name="pw" value={formData.pw} onChange={handleChange}/>
                    <div className="form-check">
                        <input type="checkbox" className="form-check-input" id="showPassword" name="showPassword" onClick={togglePassword}/>
                        <label className="form-check-label" htmlFor="showPassword">Show Password</label>
                    </div>
                </div>
                <div className="mb-3">
                    <div className="row justify-content-evenly">
                        <button className="btn btn-danger col-11 col-sm-4 mt-2 mt-sm-0" name="cancelButton"><Link to="/">Cancel</Link></button>
                        <button type="submit" className="btn btn-primary col-11 col-sm-4 mt-2">Create Game</button>
                    </div>
                </div>
            </form>
        </div>
    </>
    );
}