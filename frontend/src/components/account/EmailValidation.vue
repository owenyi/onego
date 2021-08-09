<template>
    <v-card-text>
        <p class="text-h4 text--primary">Email 확인</p><br>
        <p class="text--primary">입력하신 이메일로 전송된 메일을 확인해주세요.</p>
        <v-text-field v-model="registerEmail" disabled></v-text-field>
        <v-text-field label="확인코드" v-model="validationCode" @keyup.enter="validateEmail"></v-text-field>
        <v-btn class="btn-padding-0" rounded outlined color="grey" @click="validateEmail"> 확인 </v-btn><br>
    </v-card-text>
</template>
<script>
import Vue from 'vue'
import { Auth } from 'aws-amplify';
import { eventBus } from '../../main'
import router from '../../router'

    export default Vue.extend({
        name: 'SignUp',
        props: {
            info: Object
        },
        data: () => ({
            validationCode: '',
            registerEmail: '',
            nickname: ''
        }),
        created(){
            this.registerEmail = this.info.email;
            this.nickname = this.info.nickname;
        },
        methods: {
            confirmSignUp(username, code, nickname) {
                try {
                    Auth.confirmSignUp(username, code)
                    .then(user => {
                        router.push({ name: 'login', params: { email: username, nickname: nickname }})
                        }
                    )
                    .catch(err => {
                        console.log(err)
                        console.log(err.code)
                        if(err.code === "CodeMismatchException"){
                            this.validationCode ='';
                            alert("잘못된 코드입니다. 다시 입력해주세요");   
                        }
                    })
                } catch (error) {
                    console.log('error confirming sign up', error)
                }
            },
            validateEmail(){
                this.registerEmail;
                this.confirmSignUp(this.registerEmail, this.validationCode, this.nickname);
            }
        }
    })
</script>