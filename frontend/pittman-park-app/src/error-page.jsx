import { useRouteError } from "react-router-dom";

export default function ErrorPage({e}) {
    const error = useRouteError();
    console.error(error);

    return (
        <div id="error-page">
            <h1>Ooops!</h1>
            <p>Sorry, an unepected error has occurred.</p>
            <p>
                <i>{error.statusText || error.message}</i>
            </p>
        </div>
    );
}