//package com.example.smartassaultapplication.Tests;
//
//import com.google.android.gms.tasks.Tasks;
//import com.google.firebase.auth.FirebaseAuth;
//
//import org.junit.Test;
//
//public class TestAuthentication {
//    @Test
//    public void testSignIn() throws Exception {
//        // Set up the mock FirebaseAuth instance
//        FirebaseAuth mockAuth = mock(FirebaseAuth.class);
//        when(mockAuth.signInWithEmailAndPassword(anyString(), anyString()))
//                .thenReturn(Tasks.forResult(null));
//
//        // Set the mock FirebaseAuth instance as the global instance
//        FirebaseAuth.setInstance(mockAuth);
//
//        // Call the signIn method of your authentication class
//        String email = "big@gmail.com";
//        String password = "12345678";
//        authentication.signIn(email, password);
//
//        // Verify that the signInWithEmailAndPassword method was called with the correct arguments
//        verify(mockAuth).signInWithEmailAndPassword(email, password);
//    }
//
//}
