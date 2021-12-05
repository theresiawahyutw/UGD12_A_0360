package com.example.gd8_x_yyyy.UnitTesting;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomPresenterTest0360 {
    @Mock
    private RandomView view;
    @Mock
    private RandomService service;
    private RandomPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new RandomPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        when(view.getEmail()) .thenReturn("");
        System.out.println("Testing Pertama : Inputan Email Kosong");
        System.out.println("Email : " + view.getEmail());
        presenter.onRandomClicked();
        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsNotUseEmailFormat() throws  Exception {
        System.out.println("\n\n" + "Testing Pertama : Inputan Email Kosong");
        when(view.getEmail()) .thenReturn("therec");
        System.out.println("Email : " + view.getEmail());
        presenter.onRandomClicked();
        verify(view).showEmailError("Format Email tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsNotUseEmailFormat2() throws  Exception {
        System.out.println("\n\n" + "Testing Pertama : Inputan Email Kosong");
        when(view.getEmail()) .thenReturn("therec@gmail.Com");
        System.out.println("Email : " + view.getEmail());

        presenter.onRandomClicked();
        verify(view).showEmailError("Format Email tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Kedua : Inputan Password Kosong");
        when(view.getEmail()) .thenReturn("therec@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("");
        System.out.println("Password : "+view.getPassword());

        presenter.onRandomClicked();
        verify(view).showPasswordError("Password tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsNotAlphaNum() throws Exception{
        System.out.println("\n\n" + "Testing Kedua : Inputan Password Kosong");
        when(view.getEmail()) .thenReturn("therec@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("therec_");
        System.out.println("Password : "+view.getPassword());

        presenter.onRandomClicked();
        verify(view).showPasswordError("Format Password tidak sesuai dengan aturan");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsLess6 () throws Exception {
        System.out.println("\n\n" + "Testing Kedua : Inputan Password Kosong");
        when(view.getEmail()) .thenReturn("therec@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("ther");
        System.out.println("Password : "+view.getPassword());

        presenter.onRandomClicked();
        verify(view).showPasswordError("Password tidak boleh kurang dari 6 karakter");
    }

    @Test
    public void shouldShowErrorMessageWhenKotaAsalIsEmpty () throws Exception {
        System.out.println("\n\n" + "Testing Ketiga : Inputan Kota Asal Kosong");
        when(view.getEmail()) .thenReturn("therec@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("there1");
        System.out.println("Password : "+view.getPassword());

        when(view.getKotaAsal()) .thenReturn("");
        System.out.println("Kota Asal : "+view.getKotaAsal());

        presenter.onRandomClicked();
        verify(view).showKotaAsalError("Kota Asal tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenKotaIsLess2Word() throws Exception {
        System.out.println("\n\n" + "Testing Ketiga : Inputan Kota Asal Kosong");
        when(view.getEmail()) .thenReturn("therec@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("there1");
        System.out.println("Password : "+view.getPassword());

        when(view.getKotaAsal()) .thenReturn("Kediri");
        System.out.println("Kota Asal : "+view.getKotaAsal());

        presenter.onRandomClicked();
        verify(view).showKotaAsalError("Kota Asal harus terdiri dari 2 kata");
    }

    @Test
    public void shouldShowErrorMessageWhenKotaAsalIsNotAlphabet() throws Exception {
        System.out.println("\n\n" + "Testing Ketiga : Inputan Kota Asal Kosong");
        when(view.getEmail()) .thenReturn("therec@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPassword()) .thenReturn("there1");
        System.out.println("Password : "+view.getPassword());

        when(view.getKotaAsal()) .thenReturn("Kediri 12");
        System.out.println("Kota Asal : "+view.getKotaAsal());

        presenter.onRandomClicked();
        verify(view).showKotaAsalError("Format Kota Asal harus berupa huruf");
    }

}