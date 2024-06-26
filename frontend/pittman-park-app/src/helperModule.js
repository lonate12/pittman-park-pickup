export const baseApiUrl = "https://jhrjyr4vu4.execute-api.us-east-1.amazonaws.com/prod";

export function getUser() {
    const savedUser = window.localStorage.getItem("pittmanParkUser");
    return savedUser ? JSON.parse(savedUser) : {};
}

export const axiosConfigObj = {
    headers: {
        "Content-Type": "application/json",
        "x-api-key": "L21UTvWqsjI2hoOOXEIFY1qI28HZbK3TDGHsGI70"
    }
};

export const modalStyles = {
    content: {
        borderRadius: "5px",
        backgroundColor: "lightgrey"
    }
}

export function stripSpacesFromAll(obj) {
    let editedObj = {};
    for (const [key, value] of Object.entries(obj)) {
        if (typeof value === "string") {
            editedObj[key] = value.trim();
        } else {
            editedObj[key] = value;
        }
    }
    return editedObj;
}