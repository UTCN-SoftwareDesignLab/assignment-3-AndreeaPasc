<template>
  <v-card>
    <v-card-title>
      Patients
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addPatient">Add Patient</v-btn>
      <v-btn @click="deletePatient">Delete Patient</v-btn>
      <v-btn @click="editPatient">Edit Patient</v-btn>
      <v-btn @click="consultationSecretary">Consultations</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="patients"
      :search="search"
    ></v-data-table>
    <PatientDialog
      :opened="dialogVisible"
      :patient="selectedPatient"
      @refresh="refreshList"
    ></PatientDialog>
  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";
import router from "../router";

export default {
  name: "PatientList",
  components: { PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Date of Birth", value: "dateOfBirth" },
        { text: "Identity Card Number", value: "identityCardNo" },
        { text: "Personal Numerical Code", value: "personalNumCode" },
        { text: "Address", value: "address" },
      ],
      dialogVisible: false,
      selectedPatient: {},
    };
  },
  methods: {
    consultationSecretary(){
      router.push("./consultationSecretary");
    },

    editPatient(patient) {
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },

    deletePatient(patient) {
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },

    addPatient() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedPatient = {};
      this.books = await api.patients.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
