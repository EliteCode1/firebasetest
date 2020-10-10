package com.example.firebasetest.Dao;

import com.example.firebasetest.bean.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    private static final String COL_NAME="Users";


    // Method to save User
    public String saveUserDetails(Users user,String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    //Method to get a single UserDetails
    public Users getUserDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Users user;

        if(document.exists()) {
            user = document.toObject(Users.class);
            return user;
        }else {
            return null;
        }
    }

    //Method to get All user info
    public List<Users> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Users> user = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            user.add(document.toObject(Users.class));
        }
        return user;

    }

    // Method to Update Existing User
   public String updateUserDetails(String id,Users user) throws InterruptedException, ExecutionException {
       Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    // Method to Delete User
    public String deleteUser(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document  ID "+id+" has been deleted";
    }

}

