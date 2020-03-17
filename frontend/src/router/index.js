import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Add from '@/components/Add'
import Edit from '@/components/Edit'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/add',
      name: 'add',
      component: Add
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: Edit
    }
  ]
})
