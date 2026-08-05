import API from "../api/axiosConfig";

export const getDashboard = () => {
    return API.get("/dashboard");
};