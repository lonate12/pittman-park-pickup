import { useSearchParams } from "react-router-dom";
import { getUser } from "../helperModule";
import { Link } from "react-router-dom";
import CreateNewGameButton from "./createNewGameButton";

export default function ApiError() {
    let [searchParams, setSearchParams] = useSearchParams();
    console.log(searchParams.get("status"));
    console.log(searchParams.get("message"));
    const user = getUser();

    return (
        <>
            <div className="row app-header">
                <h1 className="welcome-header text-center col-12 col-md-4 offset-md-4 mb-1"><Link to="/">Pittman Park Pickup</Link></h1>
                <h3 className="col-12 col-md-4 text-center text-md-end">{user.role === "admin" && <CreateNewGameButton />}</h3>
                <h3 className="text-center">Hey, {user.firstName}!</h3>
            </div>
            <div className="row mt-4">
                <h3 className="text-center" style={{color: "white"}}>{searchParams.get("message").split(":")[1].trim()}</h3>
                <img className="img-fluid pittman-app-image col-sm-8 offset-sm-2" src={`https://http.cat/${searchParams.get("status")}`} alt="HTTP Status Cat Image" />
            </div>
            
        </>
    );
}