/* eslint-disable @typescript-eslint/no-explicit-any */
import axios from "axios";
import { Specialist } from "../components/SearchSpecialist";

export const getSpecialists = async(): Promise<Specialist[]> => {
    try {
        const response = await axios.get("/api/specialist");
        return response.data.content
    } catch (error: any) {
        console.error(error)
    }
    return []
}