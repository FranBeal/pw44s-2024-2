import { HomePage } from "@/pages/HomePage";
import { LoginPage } from "@/pages/LoginPage";
import { UserSignUpPage } from "@/pages/UserSignupPage";
import { Route, Routes } from "react-router-dom";

export function BaseRoutes() {
    return (
        <Routes>
            {/*Public Routes*/}
            <Route path="/signup" element={<UserSignUpPage />} />
            <Route path="/login" element={<LoginPage />} />
            {/*Protected Routes*/}
            <Route path="/" element={<HomePage />} />
            <Route path="/home" element={<HomePage />} />
        </Routes>
    )
}