import Vue from 'vue'
import Vuex from 'vuex'
import { Auth } from 'aws-amplify'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      signedIn: false,
      userInfo: new Object()
    },
    accessToken: ''
  },
  mutations: {
    changeSignedInState: function(state, user){
      Vue.set(state.user, 'signedIn', !!user)
      Vue.set(state.user, 'userInfo', user)
    },
    setAccessToken: function(state, token){
      Vue.set(state, 'accessToken', token)
    }
  },
  getters: {
    getSignedIn: function(state){
      return state.user.signedIn
    }
  },
  actions: {
    findUser: function(){
      try{
        Auth.currentAuthenticatedUser()
          .then(user => {
                this.state.user.signedIn = !!user;
                this.state.user.userInfo = user;
                Auth.currentSession()
                    .then((result: any) => {
                        this.state.accessToken = result.accessToken.jwtToken;
                })

            })
          .catch(err => {
              console.log(err)
              this.state.user.signedIn = false;
              this.state.user.userInfo = {};

          });
      }
      catch (error) {
          console.log('not signed in', error);
      }
      
    }
  },
  modules: {
  }
})