import API from "../api/axiosConfig";

export const getMyProfile = () => {

    return API.get("/employees/me");

};