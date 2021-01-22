<template>
  <div class="">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="navbar-brand" @click="goTo('/')">Wow Forum</div>
      <div class=" navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <div
            v-if="user ? user.roles.includes('ADMIN') : false"
            class="nav-item nav-link"
            @click="goTo('/admin')"
          >
            Admin sida
          </div>
          <div v-if="!user" class="nav-item nav-link" @click="goTo('/login')">
            Logga in
          </div>
          <div v-if="user" class="nav-item nav-link" @click="logOut()">
            Logga ut {{ user.username }}
          </div>
        </div>
      </div>
    </nav>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark no-pad">
      <div class=" navbar-collapse d-flex justify-content-center row no-pad-bm">
        <div
          v-for="forum in forums"
          :key="forum.id"
          :class="forum.name"
          class="nav-item nav-link col-xs-3 col-sm-3 col-md-2 col-lg-2 col-xl-1 text-center"
          @click="goToForum(forum.forum_id)"
        >
          {{ forum.name }}
        </div>
      </div>
    </nav>
  </div>
</template>
<script>
export default {
  name: "ForumNav",
  computed: {
    forums() {
      return this.$store.state.forums;
    },
    user() {
      return this.$store.state.loggedInUser;
    },
  },
  created() {
    this.$store.dispatch("fetchAllForums");
  },
  methods: {
    goTo(route) {
      if (this.$route.path != route) {
        this.$router.push({ path: route });
      }
    },
    goToForum(id) {
      this.goTo(`/forum/${id}`);
    },
    async logOut() {
      fetch("/auth/logout", {
        method: "GET",
      })
        .then((response) => {
          if (response.ok) {
            this.$store.commit("setloggedInUser", null);
            if (this.$route.path === "/") return;
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
<style>
.Warrior {
  background-color: #c69b6d;
}
.Mage {
  background-color: #3fc7eb;
}
.Paladin {
  background-color: #f48cba;
}
.Rogue {
  background-color: #fff468;
}
.Druid {
  background-color: #ff7c0a;
}
.Priest {
  background-color: #ffffff;
}
.Warlock {
  background-color: #8788ee;
}
.Hunter {
  background-color: #aad372;
}
.Shaman {
  background-color: #0070dd;
}
.DemonHunter {
  background-color: #a330c9;
}
.DeathKnight {
  background-color: #c41e3a;
}
.Monk {
  background-color: #00ff98;
}

.nav-link:hover {
  cursor: pointer;
}

.nav-item:hover {
  background-color: rgba(169, 169, 169, 0);
  color: white;
}
.no-pad {
  padding-bottom: 0 !important;
  padding-top: 0 !important;
  /* padding: 0 !important; */
}

.cont:hover {
  cursor: pointer;
  background-color: rgb(137, 139, 163);
  color: white;
}
</style>
