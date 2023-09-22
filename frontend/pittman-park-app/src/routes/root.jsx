import CreateUserPage from "../components/createUserPage";
import HomePage from "../components/homePage";

export default function Root() {
  let user = window.localStorage.getItem("pittmanParkUser");
  let elem = user != null ? <HomePage user={JSON.parse(user)} /> : <CreateUserPage />;
  return (
    <>
      {elem}
    </>
  );
}