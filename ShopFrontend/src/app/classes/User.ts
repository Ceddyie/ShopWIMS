export class User {
  _id: number;
  _username: string;
  _password: string;
  _email: string;
  _firstName: string;
  _lastName: string;
  constructor(
    public id: number,
    public username: string,
    public password: string,
    public email: string,
    public firstName: string,
    public lastName: string
  ) {
    this._id = id;
    this._username = username;
    this._password = password;
    this._email = email;
    this._firstName = firstName;
    this._lastName = lastName
  }
}
