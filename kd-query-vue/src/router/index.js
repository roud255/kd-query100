import { createRouter, createWebHistory } from 'vue-router'
import Query from "@/views/Query";

const routes = [
  {
    path: '/kdx/query',
    name: 'query',
    component: Query,
  },
  {
    path: '/',
    name: 'q',
    component: Query,
  }
    ]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

