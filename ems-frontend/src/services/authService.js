import API from "../api/axiosConfig";

export const login = (data) => {
    return API.post("/auth/login", data);
};

export const register = (data) => {
    return API.post("/auth/register", data);
};

export const changePassword = (data) => {
    return API.put("/auth/change-password", data);
};
export const forgotPassword = (email) => {
    return API.post("/auth/forgot-password", {
        email
    });
};

export const verifyOtp = (email, otp) => {
    return API.post("/auth/verify-otp", {
        email,
        otp
    });
};

export const resetPassword = (email, otp, newPassword) => {
    return API.post("/auth/reset-password", {
        email,
        otp,
        newPassword
    });
};