<template>
    <v-card-text>
        <p class="text-h4 text--primary">SIGN UP</p><br>
        <!-- <p v-if="errors.length">
            <b>아래 오류를 수정하세요.:</b>
            <ul>
            <li v-for="error in errors">{{ error }}</li>
            </ul>
        </p> -->
        <v-text-field label="이메일" v-model="registerEmail"></v-text-field>
        <v-text-field label="닉네임" v-model="nickname"></v-text-field>
        <v-text-field
            v-model="password"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'" 
            :rules="[rules.required, rules.min]"
            :type="show1 ? 'text' : 'password'"
            name="input-10-1"
            label="비밀번호"
            hint="At least 8 characters"
            counter
            @click:append="show1 = !show1"
        ></v-text-field><br>
        <v-btn class="btn-padding-0" rounded outlined color="grey" @click="signUp"> 회원가입 </v-btn><br>
    </v-card-text>
</template>
<script>
import Vue from 'vue'
import { Auth } from 'aws-amplify';

    async function signUp(email, nickname, password) {
        try {
            const { user } = Auth.signUp({
                username: email,
                password: password,
                attributes: {          // optional - E.164 number convention
                    email: email,
                    nickname: nickname,// other custom attributes 
                }
            });
        } catch (error) {
            console.log('error signing up:', error);
        }
    }

    export default Vue.extend({
        name: 'SignUp',
        props: {
            info: Object
        },
        data: () => ({
            show1: false,
            show2: true,
            show3: false,
            show4: false,
            registerEmail: '',
            password: '',
            nickname: '',
            rules: {
            required: value => !!value || 'Required.',
            min: v => v.length >= 8 || 'Min 8 characters',
            emailMatch: () => (`The email and password you entered don't match`),
            
            }
        }),
        created(){
            this.registerEmail = this.info.email;
            this.nickname = this.info.nickname;
        },
        methods: {
            signUp(){
                signUp(this.registerEmail, this.nickname, this.password);
                this.$set(this.$parent.$parent.$data.info, 'email', this.registerEmail)
                this.$set(this.$parent.$parent.$data.info, 'nickname', this.nickname)
                
            }
        }
    })
</script>