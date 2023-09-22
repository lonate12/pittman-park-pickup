import { useState } from 'react';
import { ThreeDots } from 'react-loader-spinner';

export default function AttendanceButton({ isPlaying, kickOffRsvp }) {
    const [loading, setLoading] = useState(false);
    const classString = "btn col-4 text-center"

    function handleClick(e) {
        setLoading(true);
        kickOffRsvp(setLoading);
    }

    return (
        <button className={isPlaying ? classString + " btn-danger" : classString + " btn-primary"} name={isPlaying ? "Attending" : "Join"} onClick={handleClick}>
            { loading ? <ThreeDots color="white" height="20" width="40" /> : (isPlaying ? "Leave game" : "Join")}
        </button>
    );
}