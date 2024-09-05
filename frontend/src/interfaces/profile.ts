export interface User {
  name: string;
  age: number;
  birthdate: string;
  gender: string;
  paymentMethod: string | null;
  appointments: string[];
}

export interface UserContextType {
  setUser: React.Dispatch<React.SetStateAction<User>>;
  user: User;
}
