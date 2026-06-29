import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AppBar, Toolbar, Typography, BottomNavigation, BottomNavigationAction, Container, Box, Paper } from '@mui/material';
import { Dashboard as DashboardIcon, Lineage as LineageIcon, Field as FieldIcon, Settings as SettingsIcon } from '@mui/icons-material';

const App = () => {
  const [activeTab, setActiveTab] = useState(0);
  const [lineageEvents, setLineageEvents] = useState([]);

  useEffect(() => {
    // Fetch lineage events from the API
    const fetchLineageEvents = async () => {
      try {
        const response = await fetch('/api/lineage');
        const data = await response.json();
        setLineageEvents(data);
      } catch (error) {
        console.error("Failed to fetch lineage events", error);
      }
    };
    fetchLineageEvents();
  }, []);

  return (
    <Router>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">Sovereign GitSync</Typography>
        </Toolbar>
      </AppBar>
      
      <Container>
        <Box sx={{ my: 4 }}>
          {/* Dashboard Content */}
          <Typography variant="h4">System Coherence: 99.98%</Typography>
        </Box>
      </Container>

      <Paper sx={{ position: 'fixed', bottom: 0, left: 0, right: 0, zIndex: 1000 }} elevation={3}>
        <BottomNavigation
          showLabels
          value={activeTab}
          onChange={(event, newValue) => {
            setActiveTab(newValue);
          }}
        >
          <BottomNavigationAction label="Engine" icon={<DashboardIcon />} />
          <BottomNavigationAction label="Lineage" icon={<LineageIcon />} />
          <BottomNavigationAction label="Field" icon={<FieldIcon />} />
          <BottomNavigationAction label="Settings" icon={<SettingsIcon />} />
        </BottomNavigation>
      </Paper>
    </Router>
  );
};

export default App;
