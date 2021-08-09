<template>
    <v-card-text>
        <p class="text-h4 text--primary">LOGIN</p><br>
        <v-text-field ref="email" label="이메일" v-model="email"></v-text-field>
        <v-text-field
            v-model="password"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[ rules.required, rules.min]"
            :type="show1 ? 'text' : 'password'"
            name="input-10-1"
            label="비밀번호"
            counter
            @click:append="show1 = !show1" @keyup.enter="login"
        ></v-text-field>
        <br>
        <v-btn class="btn-padding-0" rounded outlined color="grey" @click="login"> 로그인 </v-btn><br>
    </v-card-text>
</template>
<script>
import Vue from 'vue'
import { Auth } from 'aws-amplify';
import router from '../../router'
import {validateEmail} from '@/utils/validation'
        
    export default Vue.extend({
        props: {
            info: Object
        },
		data: () => ({
            email: '',
            show1: false,
            show2: true,
            show3: false,
            show4: false,
            registerEmail: '',
            password: '',
            nickname: '',
            err: {},
            rules: {
            required: value => !!value || 'Required.',
            min: v => v.length >= 8 || 'Min 8 characters',
            emailMatch: () => (`The email and password you entered don't match`),
            
            }
		}),
		name: "LoginPage",
		components:{
		},
        created(){
            this.email = this.info.email
            this.nickname = this.info.nickname
        },
        methods: {
            reset(){
                this.email = '';
                this.password='';
                this.$refs.email.focus();
            },
            login(){
                if(validateEmail(this.email)==false){
                    alert("이메일 형식이 올바르지 않습니다.");
                    this.reset();
                    return;
                }else if(this.email == '' || this.password == ''){
                    alert("이메일과 비밀번호를 모두 입력해주세요.")
                    this.reset();
                    return;
                }
                try {
                    Auth.signIn(this.email, this.password)
                            .then(user => {
                                this.$store.commit('changeSignedInState', user);
                                // this.$store.state.user.userInfo = user;
                                router.push({ name: 'Main'})
                                console.log("current Session")
                                console.log("store token before")
                                console.log(this.$store.state.accessToken)
                                Auth.currentSession()
                                    .then(result => {
                                        console.log(result)
                                        this.$store.commit('setAccessToken', result.accessToken.jwtToken);
                                    })
                                

                            })
                            .catch(err => {
                                console.log(err)
                                console.log(err.code)
                                this.err = err
                                console.log(this.err.code)
                                if(err.code === "NotAuthorizedException"){
                                        alert("이메일 혹은 비밀번호가 잘못되었습니다.");
                                        this.reset();
                                }else if(err.code === "UserNotFoundException"){
                                    alert("등록되지 않는 계정입니다.");
                                    this.reset();
                                }
                            });
                    
                } catch (error) {
                    console.log('error signing in', error);
                }
            }
        },
	})
</script>
<style>
</style>