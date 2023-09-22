import { useState } from 'react';
import axios from 'axios';
import { baseApiUrl } from '../helperModule';
import { RotatingLines } from 'react-loader-spinner';
import { axiosConfigObj } from '../helperModule';
import { useNavigate } from 'react-router-dom';


export default function CreateUserPage() {
    const [formData, setFormData] = useState({firstName: "", lastName: "", email: ""});
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevFormData) => ({...prevFormData, [name]: value}));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        console.log(`First Name: ${formData.firstName}, Last Name: ${formData.lastName}, Email: ${formData.email}`);
        // Setup request data
        const url = `${baseApiUrl}/users`;

        // Submit the post request to create a new user
        axios.post(url, formData, axiosConfigObj)
            .then((res) => {
                console.log(res.data.user);
                window.localStorage.setItem("pittmanParkUser", JSON.stringify(res.data.user));
                window.location.reload();
            });
    };

    if (loading) {
        return (
            <div className="row text-center">
                <h1 className="col-8 offset-2 text-center">
                    Give us a second while we create or find your user...
                </h1>
                <h1 className="col-8 offset-2">
                    <RotatingLines strokeColor="grey"/>
                </h1>
            </div>
        );
    }

    return (
        <div className="row">
            <div className="col-8 offset-2 text-center">
                <h1>Please fill in the below information to access the Pittman Park Pickup App</h1>
            </div>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="firstName" className="form-label">First Name</label>
                    <input 
                        type="text" 
                        className="form-control" 
                        id="firstName"
                        name="firstName" 
                        value={formData.firstName} 
                        onChange={handleChange}
                        required={true}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="lastName" className="form-label">Last Name</label>
                    <input 
                        type="text" 
                        className="form-control" 
                        id="lastName"
                        name="lastName" 
                        value={formData.lastName}
                        onChange={handleChange}
                        required={true}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label" htmlFor="email">Email</label>
                    <input 
                        type="email" 
                        className="form-control" 
                        id="email"
                        name="email" 
                        value={formData.email}
                        onChange={handleChange}
                        required={true}  
                    />
                    <div id="emailHelp" className="form-text">We'll never share your email with anyone else.</div>
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    );
}