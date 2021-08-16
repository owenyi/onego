import Vue from 'vue'
import Vuex from 'vuex'
import { Auth } from 'aws-amplify'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      signedIn: false,
      userAccount: new Object(),
      userInfo: new Object()
    },
    accessToken: '',
    followerUser: '',
    followingUser: ''
  },
  mutations: {
    changeSignedInState: function(state, user){
      Vue.set(state.user, 'signedIn', !!user)
      Vue.set(state.user, 'userAccount', user)
    },
    setAccessToken: function(state, token){
      Vue.set(state, 'accessToken', token)
    },
    setUserInfo: function(state, user){
      Vue.set(state.user, 'userInfo', user)
    },
    setFollowerUser: function(state, user){
      Vue.set(state, 'followerUser', user)
    },
    setFollowingUser: function(state, user){
      Vue.set(state, 'followingUser', user)
    }
  },
  getters: {
    getSignedIn: function(state){
      return state.user.signedIn
    }
  },
  actions: {
    findUser: async function(){
      try{
        await Auth.currentAuthenticatedUser()
          .then(user => {
                this.state.user.signedIn = !!user;
                this.state.user.userAccount = user;
                Auth.currentSession()
                    .then((result: any) => {
                        this.state.accessToken = result.accessToken.jwtToken;
                })

            })
          .catch(err => {
              console.log(err)
              this.state.user.signedIn = false;
              this.state.user.userAccount = {};

          });
      }
      catch (error) {
          console.log('not signed in', error);
      }
    },
    // setU
  },
  modules: {
  }
})