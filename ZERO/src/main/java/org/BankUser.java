    // This class will represent our bank user.

package org;


    // Here we show the advantage of object-oriented programming by
    // having our BankUser class extend BankAccount. Inheritance is
    // a KEY pillar of OOP, and it allows us to conveniently exercise
    // code re-usability and functionality from the class in which we extend.

public class BankUser extends BankAccount {

    // Private access modifier used below to restrict access so that they can only
    // be accessed by the defining class, which here, is BankUser

    private int userID;

    // The use of the  "this" keyword refers to the current object. We do this to eliminate
    // confusion between class attributes and parameters of the same name(if they exist).
    // The super keyword is used to access the parent class constructor (a special method
    // created by the "new" keyword that is used to initialize objects).
    // Super is utilizing inheritance in regard to Object-oriented programming (OOP).

    public BankUser(int uid) {
        super(uid);
        this.userID = uid;
    }
}

