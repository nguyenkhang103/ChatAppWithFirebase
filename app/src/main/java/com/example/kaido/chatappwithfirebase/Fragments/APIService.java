package com.example.kaido.chatappwithfirebase.Fragments;

import com.example.kaido.chatappwithfirebase.Notification.MyRespone;
import com.example.kaido.chatappwithfirebase.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAXH9LjVA:APA91bE5zdIbKS_T9h1cmmUaobHFhjgY86NOcNqrFvHeoZ7KI2dmu2NA3iuqyR0bvOatA3mIaas83OTgstKyibi624CqM9o7fH1xSXFV1lsRIkyml6us5J0F-RitwrRqDXJ_5BpN355d"
            }
    )
    @POST("fcm/send")
    Call<MyRespone> sendNotification(@Body Sender body);
}
