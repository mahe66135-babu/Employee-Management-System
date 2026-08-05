import API from "../api/axiosConfig";

export const getPerformances = () => {
    return API.get("/performance");
};

export const getMyPerformance = () => {
    return API.get("/performance/me");
};

export const addPerformance = (data) => {
    return API.post("/performance", data);
};

export const updatePerformance = (id, data) => {
    return API.put(`/performance/${id}`, data);
};

export const deletePerformance = (id) => {
    return API.delete(`/performance/${id}`);
};