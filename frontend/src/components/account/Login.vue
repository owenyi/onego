<template>
    <v-card-text>
        <p class="text-h4 text--primary">LOGIN</p><br>
        <v-text-field label="이메일" v-model="email"></v-text-field>
        <v-text-field
            v-model="password"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[ rules.required, rules.min]"
            :type="show1 ? 'text' : 'password'"
            name="input-10-1"
            label="비밀번호"
            counter
            @click:append="show1 = !show1"
        ></v-text-field>
        <br>
        <v-btn class="btn-padding-0" rounded outlined color="grey" @click="login"> 로그인 </v-btn><br>
    </v-card-text>
</template>
<script>
import Vue from 'vue'
import { Auth } from 'aws-amplify';
import router from '../../router'
        
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
            login(){
                try {
                    const user = Auth.signIn(this.email, this.password)
                            .then(user => {
                                this.$store.commit('changeSignedInState', user);
                                // this.$store.state.user.userInfo = user;
                                router.push({ name: 'Main'})
                            })
                            .catch(err => console.log(err));
                    
                    
                } catch (error) {
                    console.log('error signing in', error);
                }
            }
        },
	})
</script>