import React from 'react';
import { Route, Link } from 'react-router-dom';
import Home from './Home';
import Event from './Event';

const App = () => (
  <div className="app">
    <div className="app-header">
      <Link to="/"><img src='//st1.skybet.com/static/bet/img/skybet-main-logo-desktop.png' className="app-header__logo" alt="logo" /></Link>
      <div className="account-bar__right">
        <Link to="/">Show Events</Link>
      </div>
    </div>
    <div className="page-wrapper">
      <Route exact path="/" component={Home} />
      <Route exact path="/event/:id" component={Event} />
    </div>
  </div>
);

export default App;