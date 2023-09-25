import React from 'react';
import ReactDOM from 'react-dom/client';

import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom";

import Root from "./routes/root";
import ErrorPage from './error-page';
import CreateGame from './routes/createGame';
import EditGame from './routes/editGame';
import ApiError from './components/apiError';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
  },{
    path: "/games/create",
    element: <CreateGame />,
    errorElement: <ErrorPage />,
  }, {
    path: "/games/:gameId/edit",
    element: <EditGame />,
    errorElement: <ErrorPage />
  }, {
    path: "/error",
    element: <ApiError />
  }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);