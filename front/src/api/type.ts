export type Employee = {
  id: number | null;
  name: string;
  password: string;
  date: Date | null;
  part: string | null;
  rank: string | null;
  role: string | null;
};

export type Loign = {
  name: string;
  password: string;
};

export type Join = {
  name: string;
  password: string;
  role: Role;
};

export type Role = "ADMIN" | "USER";
